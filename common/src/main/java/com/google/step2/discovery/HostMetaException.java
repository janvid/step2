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
package com.google.step2.discovery;

/**
 * Exception thrown when host-meta cannnot be fetched.
 */
public class HostMetaException extends Exception {

  public HostMetaException() {
  }

  public HostMetaException(String message) {
    super(message);
  }

  public HostMetaException(Throwable cause) {
    super(cause);
  }

  public HostMetaException(String message, Throwable cause) {
    super(message, cause);
  }
}
