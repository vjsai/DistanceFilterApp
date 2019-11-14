package com.vjsai.intercom.cache;

/**
 * Caching policy for LRU Cache
 */
public enum CachingPolicy {
    THROUGH("through"), AROUND("around"), BEHIND("behind"), ASIDE("aside");

    private String policy;

    CachingPolicy(String policy) {
        this.policy = policy;
    }

    public String getPolicy() {
        return policy;
    }
}