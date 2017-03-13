package app.view;

import app.domain.UserStatus;
import app.domain.User;

import app.repository.UserRepository;
import com.vaadin.annotations.Title;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@SpringUI
@Title("CRUD Application")
public class VaadinUI extends UI {

    public static List<User> userList = new ArrayList<>(); //pagination

    @Autowired
    UserRepository userRepository; //pagination

    private final UserRepository repo;

    private final UserEditor editor;

    final Grid<User> grid;

    final TextField filter;

    private final Button addNewBtn;

    @Autowired
    public VaadinUI(UserRepository repo, UserEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(User.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New user", FontAwesome.PLUS);
    }

    @Override
    protected void init(VaadinRequest request) {

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);

        VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);
        setContent(mainLayout);

        grid.setHeight(300, Unit.PIXELS);
        grid.setWidth(700, Unit.PIXELS);
        grid.setColumns("id", "name", "age", "status", "timestamp");

        filter.setPlaceholder("Filter by name");

        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> listUsers(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editUser(e.getValue());
        });

        addNewBtn.addClickListener(e -> editor.editUser(new User("", "0", UserStatus.FALSE)));


        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listUsers(filter.getValue());
        });


        listUsers(null);
    }


    void listUsers(String filterText) {
        if (StringUtils.isEmpty(filterText)) {

            grid.setItems(repo.findAll());
        }
        else {
            grid.setItems(repo.findByNameStartsWithIgnoreCase(filterText));
        }
    }

}
