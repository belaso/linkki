/*
 * Copyright Faktor Zehn AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.linkki.core.nls.pmo;

import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.linkki.core.binding.BindingContext;
import org.linkki.core.nls.pmo.sample.NlsOtherTablePmo;
import org.linkki.core.nls.pmo.sample.NlsTablePmo;
import org.linkki.core.nls.pmo.sample.NlsTableRowPmo;
import org.linkki.core.ui.table.ContainerPmo;
import org.linkki.core.ui.table.PmoBasedTableFactory;
import org.linkki.core.ui.table.PmoBasedTableSectionFactory;
import org.linkki.core.ui.table.TableSection;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

@RunWith(MockitoJUnitRunner.class)
public class PmoNlsServiceTableSectionTest {


    @SuppressWarnings("null")
    @Mock
    private BindingContext ctx;

    @Test
    public void testTableRowLabels() {
        PmoBasedTableFactory<NlsTableRowPmo> factory = new PmoBasedTableFactory<>(new NlsTablePmo(), ctx);
        Table table = factory.createTable();
        assertThat(table, is(notNullValue()));
        // we override labels for rows one and two in linkki-messages.properties. Other two rows
        // should have labels from PMO
        assertThat(table.getColumnHeaders(), is(arrayContaining("label1", "label2", "3", "")));
    }

    @Test
    // Section caption overrides in linkki-messages.properties
    public void testTableSectionCaption() {
        assertThat(createAndGetTableSectionCaption(new NlsTablePmo()), is("Other Caption"));


    }

    @Test
    // Section caption do not overrides in linkki-messages.properties
    public void testTableSectionCaptionNoOverriding() {

        assertThat(createAndGetTableSectionCaption(new NlsOtherTablePmo()), is("Some Caption"));
    }


    private String createAndGetTableSectionCaption(ContainerPmo<NlsTableRowPmo> container) {
        PmoBasedTableSectionFactory<NlsTableRowPmo> factory = new PmoBasedTableSectionFactory<NlsTableRowPmo>(
                container, ctx);
        TableSection<NlsTableRowPmo> tableSection = factory.createSection();
        HorizontalLayout header = (HorizontalLayout)tableSection.getComponent(0);
        Label l = (Label)header.getComponent(0);
        return l.getValue();

    }


}
