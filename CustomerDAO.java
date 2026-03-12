<?xml version="1.0" encoding="UTF-8" ?>

<Form version="1.5" maxVersion="1.9" type="org.netbeans.modules.form.forminfo.JFrameFormInfo">
  <NonVisualComponents>
    <Container class="javax.swing.JScrollPane" name="jScrollPane2">
      <AuxValues>
        <AuxValue name="autoScrollPane" type="java.lang.Boolean" value="true"/>
      </AuxValues>

      <Layout class="org.netbeans.modules.form.compat2.layouts.support.JScrollPaneSupportLayout"/>
      <SubComponents>
        <Component class="javax.swing.JList" name="jList1">
          <Properties>
            <Property name="model" type="javax.swing.ListModel" editor="org.netbeans.modules.form.editors2.ListModelEditor">
              <StringArray count="5">
                <StringItem index="0" value="Item 1"/>
                <StringItem index="1" value="Item 2"/>
                <StringItem index="2" value="Item 3"/>
                <StringItem index="3" value="Item 4"/>
                <StringItem index="4" value="Item 5"/>
              </StringArray>
            </Property>
          </Properties>
          <AuxValues>
            <AuxValue name="JavaCodeGenerator_TypeParameters" type="java.lang.String" value="&lt;String&gt;"/>
          </AuxValues>
        </Component>
      </SubComponents>
    </Container>
  </NonVisualComponents>
  <Properties>
    <Property name="defaultCloseOperation" type="int" value="3"/>
  </Properties>
  <SyntheticProperties>
    <SyntheticProperty name="formSizePolicy" type="int" value="1"/>
    <SyntheticProperty name="generateCenter" type="boolean" value="false"/>
  </SyntheticProperties>
  <AuxValues>
    <AuxValue name="FormSettings_autoResourcing" type="java.lang.Integer" value="0"/>
    <AuxValue name="FormSettings_autoSetComponentName" type="java.lang.Boolean" value="false"/>
    <AuxValue name="FormSettings_generateFQN" type="java.lang.Boolean" value="true"/>
    <AuxValue name="FormSettings_generateMnemonicsCode" type="java.lang.Boolean" value="false"/>
    <AuxValue name="FormSettings_i18nAutoMode" type="java.lang.Boolean" value="false"/>
    <AuxValue name="FormSettings_layoutCodeTarget" type="java.lang.Integer" value="1"/>
    <AuxValue name="FormSettings_listenerGenerationStyle" type="java.lang.Integer" value="3"/>
    <AuxValue name="FormSettings_variablesLocal" type="java.lang.Boolean" value="false"/>
    <AuxValue name="FormSettings_variablesModifier" type="java.lang.Integer" value="2"/>
    <AuxValue name="designerSize" type="java.awt.Dimension" value="-84,-19,0,5,115,114,0,18,106,97,118,97,46,97,119,116,46,68,105,109,101,110,115,105,111,110,65,-114,-39,-41,-84,95,68,20,2,0,2,73,0,6,104,101,105,103,104,116,73,0,5,119,105,100,116,104,120,112,0,0,1,-12,0,0,3,32"/>
  </AuxValues>

  <Layout class="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout"/>
  <SubComponents>
    <Container class="javax.swing.JPanel" name="jPanel1">
      <Properties>
        <Property name="background" type="java.awt.Color" editor="org.netbeans.beaninfo.editors.ColorEditor">
          <Color blue="99" green="ff" red="99" type="rgb"/>
        </Property>
        <Property name="preferredSize" type="java.awt.Dimension" editor="org.netbeans.beaninfo.editors.DimensionEditor">
          <Dimension value="[800, 60]"/>
        </Property>
      </Properties>
      <Constraints>
        <Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout" value="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout$BorderConstraintsDescription">
          <BorderConstraints direction="First"/>
        </Constraint>
      </Constraints>

      <Layout>
        <DimensionLayout dim="0">
          <Group type="103" groupAlignment="0" attributes="0">
              <EmptySpace min="0" pref="800" max="32767" attributes="0"/>
          </Group>
        </DimensionLayout>
        <DimensionLayout dim="1">
          <Group type="103" groupAlignment="0" attributes="0">
              <EmptySpace min="0" pref="60" max="32767" attributes="0"/>
          </Group>
        </DimensionLayout>
      </Layout>
    </Container>
    <Container class="javax.swing.JPanel" name="jPanel2">
      <Constraints>
        <Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout" value="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout$BorderConstraintsDescription">
          <BorderConstraints direction="Center"/>
        </Constraint>
      </Constraints>

      <Layout>
        <DimensionLayout dim="0">
          <Group type="103" groupAlignment="0" attributes="0">
              <Group type="102" alignment="0" attributes="0">
                  <Component id="jPanel4" min="-2" pref="309" max="-2" attributes="0"/>
                  <EmptySpace min="0" pref="0" max="-2" attributes="0"/>
                  <Component id="jPanel3" pref="491" max="32767" attributes="0"/>
              </Group>
          </Group>
        </DimensionLayout>
        <DimensionLayout dim="1">
          <Group type="103" groupAlignment="0" attributes="0">
              <Component id="jPanel3" alignment="0" pref="440" max="32767" attributes="0"/>
              <Component id="jPanel4" alignment="1" max="32767" attributes="0"/>
          </Group>
        </DimensionLayout>
      </Layout>
      <SubComponents>
        <Container class="javax.swing.JPanel" name="jPanel4">

          <Layout class="org.netbeans.modules.form.compat2.layouts.DesignGridLayout">
            <Property name="columns" type="int" value="1"/>
            <Property name="rows" type="int" value="4"/>
          </Layout>
          <SubComponents>
            <Container class="javax.swing.JPanel" name="AddServicePanel">
              <Properties>
                <Property name="background" type="java.awt.Color" editor="org.netbeans.beaninfo.editors.ColorEditor">
                  <Color blue="ff" green="ff" red="99" type="rgb"/>
                </Property>
                <Property name="border" type="javax.swing.border.Border" editor="org.netbeans.modules.form.editors2.BorderEditor">
                  <Border info="org.netbeans.modules.form.compat2.border.EtchedBorderInfo">
                    <EtchetBorder/>
                  </Border>
                </Property>
                <Property name="focusCycleRoot" type="boolean" value="true"/>
              </Properties>

              <Layout class="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout"/>
              <SubComponents>
                <Component class="javax.swing.JLabel" name="AddServiceTrigger">
                  <Properties>
                    <Property name="background" type="java.awt.Color" editor="org.netbeans.beaninfo.editors.ColorEditor">
                      <Color blue="ff" green="ff" red="99" type="rgb"/>
                    </Property>
                    <Property name="horizontalAlignment" type="int" value="0"/>
                    <Property name="text" type="java.lang.String" value="ADD SERVICE"/>
                    <Property name="horizontalTextPosition" type="int" value="0"/>
                  </Properties>
                  <Events>
                    <EventHandler event="mouseClicked" listener="java.awt.event.MouseListener" parameters="java.awt.event.MouseEvent" handler="AddServiceTriggerMouseClicked"/>
                    <EventHandler event="mouseEntered" listener="java.awt.event.MouseListener" parameters="java.awt.event.MouseEvent" handler="AddServiceTriggerMouseEntered"/>
                    <EventHandler event="mouseExited" listener="java.awt.event.MouseListener" parameters="java.awt.event.MouseEvent" handler="AddServiceTriggerMouseExited"/>
                  </Events>
                  <Constraints>
                    <Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout" value="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout$BorderConstraintsDescription">
                      <BorderConstraints direction="Center"/>
                    </Constraint>
                  </Constraints>
                </Component>
              </SubComponents>
            </Container>
            <Container class="javax.swing.JPanel" name="EditServicePanel">
              <Properties>
                <Property name="background" type="java.awt.Color" editor="org.netbeans.beaninfo.editors.ColorEditor">
                  <Color blue="ff" green="ff" red="99" type="rgb"/>
                </Property>
                <Property name="border" type="javax.swing.border.Border" editor="org.netbeans.modules.form.editors2.BorderEditor">
                  <Border info="org.netbeans.modules.form.compat2.border.EtchedBorderInfo">
                    <EtchetBorder/>
                  </Border>
                </Property>
              </Properties>

              <Layout class="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout"/>
              <SubComponents>
                <Component class="javax.swing.JLabel" name="EditServiceTrigger">
                  <Properties>
                    <Property name="horizontalAlignment" type="int" value="0"/>
                    <Property name="text" type="java.lang.String" value="EDIT SERVICE"/>
                  </Properties>
                  <Events>
                    <EventHandler event="mouseClicked" listener="java.awt.event.MouseListener" parameters="java.awt.event.MouseEvent" handler="EditServiceTriggerMouseClicked"/>
                    <EventHandler event="mouseEntered" listener="java.awt.event.MouseListener" parameters="java.awt.event.MouseEvent" handler="EditServiceTriggerMouseEntered"/>
                    <EventHandler event="mouseExited" listener="java.awt.event.MouseListener" parameters="java.awt.event.MouseEvent" handler="EditServiceTriggerMouseExited"/>
                  </Events>
                  <Constraints>
                    <Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout" value="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout$BorderConstraintsDescription">
                      <BorderConstraints direction="Center"/>
                    </Constraint>
                  </Constraints>
                </Component>
              </SubComponents>
            </Container>
            <Container class="javax.swing.JPanel" name="RemoveServicePanel">
              <Properties>
                <Property name="background" type="java.awt.Color" editor="org.netbeans.beaninfo.editors.ColorEditor">
                  <Color blue="ff" green="ff" red="99" type="rgb"/>
                </Property>
                <Property name="border" type="javax.swing.border.Border" editor="org.netbeans.modules.form.editors2.BorderEditor">
                  <Border info="org.netbeans.modules.form.compat2.border.EtchedBorderInfo">
                    <EtchetBorder/>
                  </Border>
                </Property>
              </Properties>

              <Layout class="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout"/>
              <SubComponents>
                <Component class="javax.swing.JLabel" name="RemoveServiceTrigger">
                  <Properties>
                    <Property name="horizontalAlignment" type="int" value="0"/>
                    <Property name="text" type="java.lang.String" value="REMOVE SERVICE"/>
                  </Properties>
                  <Events>
                    <EventHandler event="mouseClicked" listener="java.awt.event.MouseListener" parameters="java.awt.event.MouseEvent" handler="RemoveServiceTriggerMouseClicked"/>
                    <EventHandler event="mouseEntered" listener="java.awt.event.MouseListener" parameters="java.awt.event.MouseEvent" handler="RemoveServiceTriggerMouseEntered"/>
                    <EventHandler event="mouseExited" listener="java.awt.event.MouseListener" parameters="java.awt.event.MouseEvent" handler="RemoveServiceTriggerMouseExited"/>
                  </Events>
                  <Constraints>
                    <Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout" value="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout$BorderConstraintsDescription">
                      <BorderConstraints direction="Center"/>
                    </Constraint>
                  </Constraints>
                </Component>
              </SubComponents>
            </Container>
            <Container class="javax.swing.JPanel" name="DashboardPanel">
              <Properties>
                <Property name="background" type="java.awt.Color" editor="org.netbeans.beaninfo.editors.ColorEditor">
                  <Color blue="ff" green="ff" red="99" type="rgb"/>
                </Property>
                <Property name="border" type="javax.swing.border.Border" editor="org.netbeans.modules.form.editors2.BorderEditor">
                  <Border info="org.netbeans.modules.form.compat2.border.EtchedBorderInfo">
                    <EtchetBorder/>
                  </Border>
                </Property>
              </Properties>

              <Layout class="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout"/>
              <SubComponents>
                <Component class="javax.swing.JLabel" name="DashboardTrigger">
                  <Properties>
                    <Property name="horizontalAlignment" type="int" value="0"/>
                    <Property name="text" type="java.lang.String" value="DASHBOARD"/>
                    <Property name="toolTipText" type="java.lang.String" value=""/>
                    <Property name="cursor" type="java.awt.Cursor" editor="org.netbeans.modules.form.editors2.CursorEditor">
                      <Color id="Hand Cursor"/>
                    </Property>
                    <Property name="horizontalTextPosition" type="int" value="0"/>
                  </Properties>
                  <Events>
                    <EventHandler event="mouseEntered" listener="java.awt.event.MouseListener" parameters="java.awt.event.MouseEvent" handler="DashboardTriggerMouseEntered"/>
                    <EventHandler event="mouseExited" listener="java.awt.event.MouseListener" parameters="java.awt.event.MouseEvent" handler="DashboardTriggerMouseExited"/>
                  </Events>
                  <Constraints>
                    <Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout" value="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout$BorderConstraintsDescription">
                      <BorderConstraints direction="Center"/>
                    </Constraint>
                  </Constraints>
                </Component>
              </SubComponents>
            </Container>
          </SubComponents>
        </Container>
        <Container class="javax.swing.JPanel" name="jPanel3">

          <Layout class="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout"/>
          <SubComponents>
            <Container class="javax.swing.JPanel" name="jPanel9">
              <Properties>
                <Property name="preferredSize" type="java.awt.Dimension" editor="org.netbeans.beaninfo.editors.DimensionEditor">
                  <Dimension value="[486, 60]"/>
                </Property>
              </Properties>
              <Constraints>
                <Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout" value="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout$BorderConstraintsDescription">
                  <BorderConstraints direction="First"/>
                </Constraint>
              </Constraints>

              <Layout>
                <DimensionLayout dim="0">
                  <Group type="103" groupAlignment="0" attributes="0">
                      <Group type="102" alignment="0" attributes="0">
                          <Component id="jPanel13" pref="342" max="32767" attributes="0"/>
                          <EmptySpace min="0" pref="0" max="-2" attributes="0"/>
                          <Component id="jPanel14" min="-2" pref="149" max="-2" attributes="0"/>
                      </Group>
                  </Group>
                </DimensionLayout>
                <DimensionLayout dim="1">
                  <Group type="103" groupAlignment="0" attributes="0">
                      <Group type="102" alignment="1" attributes="0">
                          <EmptySpace min="0" pref="0" max="32767" attributes="0"/>
                          <Group type="103" groupAlignment="1" attributes="0">
                              <Component id="jPanel14" alignment="1" min="-2" pref="60" max="-2" attributes="0"/>
                              <Component id="jPanel13" alignment="1" min="-2" max="-2" attributes="0"/>
                          </Group>
                          <EmptySpace min="-2" pref="40" max="-2" attributes="0"/>
                      </Group>
                  </Group>
                </DimensionLayout>
              </Layout>
              <SubComponents>
                <Container class="javax.swing.JPanel" name="jPanel13">
                  <Properties>
                    <Property name="preferredSize" type="java.awt.Dimension" editor="org.netbeans.beaninfo.editors.DimensionEditor">
                      <Dimension value="[326, 60]"/>
                    </Property>
                  </Properties>

                  <Layout>
                    <DimensionLayout dim="0">
                      <Group type="103" groupAlignment="0" attributes="0">
                          <Component id="jTextField1" pref="342" max="32767" attributes="0"/>
                      </Group>
                    </DimensionLayout>
                    <DimensionLayout dim="1">
                      <Group type="103" groupAlignment="0" attributes="0">
                          <Component id="jTextField1" min="-2" pref="60" max="-2" attributes="0"/>
                      </Group>
                    </DimensionLayout>
                  </Layout>
                  <SubComponents>
                    <Component class="javax.swing.JTextField" name="jTextField1">
                      <Events>
                        <EventHandler event="actionPerformed" listener="java.awt.event.ActionListener" parameters="java.awt.event.ActionEvent" handler="jTextField1ActionPerformed"/>
                      </Events>
                    </Component>
                  </SubComponents>
                </Container>
                <Container class="javax.swing.JPanel" name="jPanel14">

                  <Layout class="org.netbeans.modules.form.compat2.layouts.DesignGridLayout">
                    <Property name="columns" type="int" value="0"/>
                    <Property name="horizontalGap" type="int" value="5"/>
                    <Property name="rows" type="int" value="1"/>
                  </Layout>
                  <SubComponents>
                    <Component class="javax.swing.JButton" name="jButton3">
                      <Properties>
                        <Property name="background" type="java.awt.Color" editor="org.netbeans.beaninfo.editors.ColorEditor">
                          <Color blue="cc" green="cc" red="cc" type="rgb"/>
                        </Property>
                        <Property name="text" type="java.lang.String" value="Search"/>
                        <Property name="border" type="javax.swing.border.Border" editor="org.netbeans.modules.form.editors2.BorderEditor">
                          <Border info="org.netbeans.modules.form.compat2.border.EtchedBorderInfo">
                            <EtchetBorder/>
                          </Border>
                        </Property>
                        <Property name="preferredSize" type="java.awt.Dimension" editor="org.netbeans.beaninfo.editors.DimensionEditor">
                          <Dimension value="[75, 100]"/>
                        </Property>
                      </Properties>
                    </Component>
                    <Component class="javax.swing.JButton" name="jButton4">
                      <Properties>
                        <Property name="background" type="java.awt.Color" editor="org.netbeans.beaninfo.editors.ColorEditor">
                          <Color blue="cc" green="cc" red="cc" type="rgb"/>
                        </Property>
                        <Property name="text" type="java.lang.String" value="Reset"/>
                        <Property name="border" type="javax.swing.border.Border" editor="org.netbeans.modules.form.editors2.BorderEditor">
                          <Border info="org.netbeans.modules.form.compat2.border.EtchedBorderInfo">
                            <EtchetBorder/>
                          </Border>
                        </Property>
                      </Properties>
                      <Events>
                        <EventHandler event="actionPerformed" listener="java.awt.event.ActionListener" parameters="java.awt.event.ActionEvent" handler="jButton4ActionPerformed"/>
                      </Events>
                    </Component>
                  </SubComponents>
                </Container>
              </SubComponents>
            </Container>
            <Container class="javax.swing.JScrollPane" name="jScrollPane1">
              <AuxValues>
                <AuxValue name="autoScrollPane" type="java.lang.Boolean" value="true"/>
              </AuxValues>
              <Constraints>
                <Constraint layoutClass="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout" value="org.netbeans.modules.form.compat2.layouts.DesignBorderLayout$BorderConstraintsDescription">
                  <BorderConstraints direction="Center"/>
                </Constraint>
              </Constraints>

              <Layout class="org.netbeans.modules.form.compat2.layouts.support.JScrollPaneSupportLayout"/>
              <SubComponents>
                <Component class="javax.swing.JTable" name="jTable1">
                  <Properties>
                    <Property name="border" type="javax.swing.border.Border" editor="org.netbeans.modules.form.editors2.BorderEditor">
                      <Border info="org.netbeans.modules.form.compat2.border.EmptyBorderInfo">
                        <EmptyBorder/>
                      </Border>
                    </Property>
                    <Property name="model" type="javax.swing.table.TableModel" editor="org.netbeans.modules.form.editors2.TableModelEditor">
                      <Table columnCount="4" rowCount="4">
                        <Column editable="true" title="Service Name" type="java.lang.Object"/>
                        <Column editable="true" title="Price" type="java.lang.Object"/>
                        <Column editable="true" title="Duration" type="java.lang.Object"/>
                        <Column editable="true" title="Status" type="java.lang.Object"/>
                      </Table>
                    </Property>
                    <Property name="columnModel" type="javax.swing.table.TableColumnModel" editor="org.netbeans.modules.form.editors2.TableColumnModelEditor">
                      <TableColumnModel selectionModel="0">
                        <Column maxWidth="-1" minWidth="-1" prefWidth="-1" resizable="true">
                          <Title/>
                          <Editor/>
                          <Renderer/>
                        </Column>
                        <Column maxWidth="-1" minWidth="-1" prefWidth="-1" resizable="true">
                          <Title/>
                          <Editor/>
                          <Renderer/>
                        </Column>
                        <Column maxWidth="-1" minWidth="-1" prefWidth="-1" resizable="true">
                          <Title/>
                          <Editor/>
                          <Renderer/>
                        </Column>
                        <Column maxWidth="-1" minWidth="-1" prefWidth="-1" resizable="true">
                          <Title/>
                          <Editor/>
                          <Renderer/>
                        </Column>
                      </TableColumnModel>
                    </Property>
                    <Property name="tableHeader" type="javax.swing.table.JTableHeader" editor="org.netbeans.modules.form.editors2.JTableHeaderEditor">
                      <TableHeader reorderingAllowed="false" resizingAllowed="true"/>
                    </Property>
                  </Properties>
                </Component>
              </SubComponents>
            </Container>
          </SubComponents>
        </Container>
      </SubComponents>
    </Container>
  </SubComponents>
</Form>
