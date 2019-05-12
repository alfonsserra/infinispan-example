package com.systelab.infinispan;

import com.systelab.infinispan.model.Patient;
import com.systelab.infinispan.respository.PatientRepository;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;

public class ExampleApp {
    private PatientRepository repository;
    private DefaultCacheManager cacheManager;
    private Cache<String, Patient> patientsCache;

    private <K, V> Cache<K, V> buildCache(String cacheName, DefaultCacheManager cacheManager,
                                          CacheListener listener, Configuration configuration) {
        cacheManager.defineConfiguration(cacheName, configuration);
        Cache<K, V> cache = cacheManager.getCache(cacheName);
        cache.addListener(listener);
        return cache;
    }

    private Cache<String, Patient> simpleCache(DefaultCacheManager cacheManager, String cacheName, CacheListener listener) {
        return this.buildCache(cacheName, cacheManager, listener, new ConfigurationBuilder().build());
    }

    public Patient findPatient(String id) {
        Patient value = patientsCache.get(id);
        if (value == null) {
            value = this.repository.getPatient(id);
            patientsCache.put(id, value);
        }
        return value;
    }

    public ExampleApp() {
        repository = new PatientRepository();
        cacheManager = new DefaultCacheManager();
        patientsCache = simpleCache(cacheManager, "patients", new CacheListener());
    }

    public static void main(String[] args) {
        ExampleApp example = new ExampleApp();
        for (int i = 0; i < 5; i++) {
            Patient value = example.findPatient("1212423e-1");
            System.out.println(value.getFullName());
        }
    }
}
