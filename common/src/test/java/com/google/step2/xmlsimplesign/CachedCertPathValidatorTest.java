/**
 * Copyright 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.google.step2.xmlsimplesign;

import java.security.cert.X509Certificate;
import java.util.List;

import junit.framework.TestCase;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.step2.util.FakeTimeSource;

/**
 *
 */
public class CachedCertPathValidatorTest extends TestCase {

  private FakeTimeSource timeSource;
  private CachedCertPathValidator validator;
  private List<X509Certificate> serverChain = Lists.newArrayList(
      CertConstantUtil.SERVER_PUB_CERT, CertConstantUtil.INTERMEDIATE_PUB_CERT);

  @Override
  public void setUp() throws Exception {
    timeSource = new FakeTimeSource(1233465103000L); // Jan 31, 2009
    validator = new CachedCertPathValidator(ImmutableSet.of(CertConstantUtil.CA_PUB_CERT));
    validator.setTimeSource(timeSource);
  }

  public void testVerify() throws Exception {
    validator.validate(serverChain);
  }

  public void testOutOfOrder() throws Exception {
    try {
      validator.validate(Lists.newArrayList(
          CertConstantUtil.INTERMEDIATE_PUB_CERT, CertConstantUtil.SERVER_PUB_CERT));
      fail("Should have thrown, certs out of order");
    } catch (CertValidatorException e) {
      // good
    }
  }

  public void testIncomplete() throws Exception {
    try {
      validator.validate(Lists.newArrayList(CertConstantUtil.SERVER_PUB_CERT));
      fail("Should have thrown, cert chain incomplete.");
    } catch (CertValidatorException e) {
      // good
    }
  }

  public void testExpired() throws Exception {
    timeSource.advanceSeconds(1800L * 24 * 60 * 60); // 1800 days
    validator.validate(serverChain);
    timeSource.advanceSeconds(2000L * 24 * 60 * 60); // 2000 days
    try {
      validator.validate(serverChain);
      fail("Should have thrown, cert expired");
    } catch (CertValidatorException e) {
      // good
    }
  }

  public void testSpeed() throws Exception {
    long start = System.currentTimeMillis();
    long ops = 0;
    while (System.currentTimeMillis() < start + 1000L) {
      validator.validate(serverChain);
      ++ops;
    }
    long stop = System.currentTimeMillis();
    System.out.println(ops/(stop-start) + " ops per ms");
  }
}
