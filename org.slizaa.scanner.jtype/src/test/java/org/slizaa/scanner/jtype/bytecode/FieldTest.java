package org.slizaa.scanner.jtype.bytecode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.slizaa.scanner.core.spi.parser.model.INode;
import org.slizaa.scanner.core.spi.parser.model.resource.CoreModelRelationshipType;
import org.slizaa.scanner.jtype.model.JTypeLabel;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;

public class FieldTest extends AbstractByteBuddyBytecodeTest {

  @Test
  public void field_1() {

    INode node = parse(
        () -> new ByteBuddy().subclass(Object.class).defineField("hurz", INode.class, Visibility.PRIVATE));

    assertThat(node.getRelationships(CoreModelRelationshipType.CONTAINS)).hasSize(2);

    //
    INode fieldNode = node.getRelationships(CoreModelRelationshipType.CONTAINS).get(0).getTargetBean();

    System.out.println(fieldNode.getProperties());
    
    assertThat(fieldNode.getLabels()).containsOnly(JTypeLabel.Field);
    assertThat(fieldNode.getProperty("name")).isEqualTo("hurz");
  }
}