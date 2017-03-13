package app.view;

import app.domain.UserStatus;
import app.domain.User;
import app.repository.UserRepository;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class UserEditor extends VerticalLayout {

    private final UserRepository repository;

    private User user;

    TextField name = new TextField("Name");
    TextField age = new TextField("Age");
    private NativeSelect<UserStatus> status = new NativeSelect<>("Is Admin");

    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(save, cancel, delete);

    Binder<User> binder = new Binder<>(User.class);

    @Autowired
    public UserEditor(UserRepository repository) {
        this.repository = repository;

        status.setItems(UserStatus.values());
        status.setEmptySelectionAllowed(false);
        addComponents(name, age, status, actions);

        binder.bindInstanceFields(this);

        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        save.addClickListener(e -> repository.save(user));
        delete.addClickListener(e -> repository.delete(user));
        cancel.addClickListener(e -> editUser(user));
        setVisible(false);
    }

    public interface ChangeHandler {

        void onChange();
    }

    public final void editUser(User u) {
        if (u == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = u.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            user = repository.findOne(u.getId());
        }
        else {
            user = u;
        }
        cancel.setVisible(persisted);
        binder.setBean(user);

        setVisible(true);

        save.focus();
        name.selectAll();
    }

    public void setChangeHandler(ChangeHandler h) {

        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }

}
