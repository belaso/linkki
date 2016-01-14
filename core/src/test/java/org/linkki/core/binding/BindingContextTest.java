package org.linkki.core.binding;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.faktorips.runtime.MessageList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.linkki.core.binding.dispatcher.ExceptionPropertyDispatcher;
import org.linkki.core.binding.dispatcher.ReflectionPropertyDispatcher;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@RunWith(MockitoJUnitRunner.class)
public class BindingContextTest {

    private BindingContext context;

    @Mock
    private Label label1;
    private Label label2;
    private TestPmo pmo = new TestPmo();
    private Field<String> field1 = spy(new TextField());
    private Field<String> field2 = spy(new TextField());

    private FieldBinding<String> binding1;
    private FieldBinding<String> binding2;

    @Before
    public void setUp() {
        context = TestBindingContext.create();
        binding1 = new FieldBinding<String>(context, pmo, "value", label1, field1,
                new ReflectionPropertyDispatcher(this::getPmo, new ExceptionPropertyDispatcher(pmo)));
        binding2 = new FieldBinding<String>(context, pmo, "value", label2, field2,
                new ReflectionPropertyDispatcher(this::getPmo, new ExceptionPropertyDispatcher(pmo)));
    }

    private TestPmo getPmo() {
        return pmo;
    }

    @Test
    public void testAdd() {
        assertEquals(0, context.getElementBindings().size());
        context.add(binding1);
        assertEquals(1, context.getElementBindings().size());
    }

    @Test
    public void testUpdateUI() {
        binding1 = spy(binding1);

        context.updateUI();
        verify(binding1, never()).updateFromPmo();
        verify(binding1, never()).displayMessages(any(MessageList.class));

        context.add(binding1);

        context.updateUI();
        verify(binding1).updateFromPmo();
        verify(binding1).displayMessages(any(MessageList.class));
    }

    @Test
    public void testChangeBoundObject() {
        binding1 = spy(binding1);

        context.updateUI();
        verify(binding1, never()).updateFromPmo();

        context.add(binding1);

        context.updateUI();
        verify(binding1).updateFromPmo();
    }

    @Test
    public void testRemoveBindingsForPmo() {
        context.add(binding1);
        context.add(binding2);

        assertThat(context.getElementBindings(), hasSize(2));

        context.removeBindingsForPmo(pmo);
        assertThat(context.getElementBindings(), is(empty()));
    }

    @Test
    public void testRemoveBindingsForComponent() {
        context.add(binding1);
        context.add(binding2);

        assertThat(context.getElementBindings(), hasSize(2));

        VerticalLayout layout = new VerticalLayout(field1, field2);

        context.removeBindingsForComponent(layout);
        assertThat(context.getElementBindings(), is(empty()));
    }
}
