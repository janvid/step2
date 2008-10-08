package com.google.step2.example.provider;

import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthException;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class Step2OAuthProvider {
  private static final OAuthConsumer CONSUMER =
    new OAuthConsumer(null, "DummyConsumer", "DummySecret", null);

  private static final ConcurrentHashMap<String, OAuthAccessor>
    ACCESSORS = new ConcurrentHashMap<String, OAuthAccessor>();
  
  public static OAuthConsumer getConsumer(String consumerKey) {
    // Just one consumer for now
    return CONSUMER;
  }
  

  public static void putAccessor(String token, OAuthAccessor accessor) {
    ACCESSORS.putIfAbsent(token, accessor);
  }
  
  public static OAuthAccessor getAccessor(String token) {
    return ACCESSORS.get(token);
  }

  public static void removeAccessor(String token) {
    ACCESSORS.remove(token);
  }
  
  public static void authorizeAccessor(String token) {
    OAuthAccessor accessor = ACCESSORS.remove(token);
    if (accessor != null) {
      accessor.setProperty("authorized", Boolean.TRUE);
    }
    putAccessor(token, accessor);
  }
}
