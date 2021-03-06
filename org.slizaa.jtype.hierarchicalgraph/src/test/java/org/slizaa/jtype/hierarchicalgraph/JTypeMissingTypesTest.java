/**
 *
 */
package org.slizaa.jtype.hierarchicalgraph;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slizaa.scanner.testfwk.ContentDefinitionProviderFactory.multipleBinaryMvnArtifacts;

import java.util.List;

import org.junit.ClassRule;
import org.junit.Test;
import org.slizaa.core.boltclient.testfwk.BoltClientConnectionRule;
import org.slizaa.hierarchicalgraph.core.model.HGNode;
import org.slizaa.hierarchicalgraph.core.model.HGRootNode;
import org.slizaa.hierarchicalgraph.graphdb.mapping.service.MappingFactory;
import org.slizaa.hierarchicalgraph.graphdb.model.GraphDbNodeSource;
import org.slizaa.jtype.hierarchicalgraph.utils.JTypeSlizaaTestServerRule;

/**
 * <p>
 * </p>
 *
 * @author Gerd W&uuml;therich (gerd@gerd-wuetherich.de)
 *
 */
public class JTypeMissingTypesTest {

  @ClassRule
  public static JTypeSlizaaTestServerRule SERVER = new JTypeSlizaaTestServerRule(
      multipleBinaryMvnArtifacts(new String[] { "com.google.guava", "guava", "23.6.1-jre" }));

  @ClassRule
  public static BoltClientConnectionRule  CLIENT = new BoltClientConnectionRule();

  @Test
  public void testMissingNodes() {

    //
    HGRootNode rootNode = MappingFactory.createMappingServiceForStandaloneSetup()
        .convert(new JType_Hierarchical_MappingProvider(), CLIENT.getBoltClient(), null);

    //
    List<Long> missingTypes = CLIENT.getBoltClient().syncExecCypherQuery("MATCH (t:MissingType) RETURN t")
        .list(record -> record.get("t").asNode().id());

    //
    assertThat(missingTypes).hasSize(349);
    for (Long missingType : missingTypes) {
      HGNode node = rootNode.lookupNode(missingType);
      assertThat(node).isNotNull();
    }

    //
    for (HGNode node : rootNode.getChildren()) {
      System.out.println(node.getNodeSource(GraphDbNodeSource.class).get().getProperties());
    }
  }
}
