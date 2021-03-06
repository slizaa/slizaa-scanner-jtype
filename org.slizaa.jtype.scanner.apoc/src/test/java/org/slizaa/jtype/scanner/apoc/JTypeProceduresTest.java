/*******************************************************************************
 * Copyright (c) 2011-2015 Slizaa project team. All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Slizaa project team - initial API and implementation
 ******************************************************************************/
package org.slizaa.jtype.scanner.apoc;

import static org.slizaa.scanner.testfwk.ContentDefinitionProviderFactory.multipleBinaryMvnArtifacts;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.slizaa.core.boltclient.testfwk.BoltClientConnectionRule;

public class JTypeProceduresTest {

  @ClassRule
  public static JTypeSlizaaTestServerRule _server = new JTypeSlizaaTestServerRule(
      multipleBinaryMvnArtifacts(new String[] { "com.netflix.eureka", "eureka-core", "1.8.2" },
          new String[] { "com.netflix.eureka", "eureka-client", "1.8.2" }));

  @Rule
  public BoltClientConnectionRule         _client = new BoltClientConnectionRule();

  /**
   * <p>
   * </p>
   */
  @Test
  public void testJtypeProcedures() {

    //
    // StatementResult statementResult = this._client.getBoltClient().syncExecCypherQuery("CALL slizaa.jtype.test()");
    // statementResult.forEachRemaining(record -> System.out.println(record));
  }
}
