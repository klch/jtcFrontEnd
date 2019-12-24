package pet.frontend.testcase2.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pet.frontend.testcase2.entity.User;

import java.util.Map;
import java.util.UUID;


@Route(value="adduser")
public class AddUserView extends VerticalLayout {
    public static Logger logger = LoggerFactory.getLogger(AddUserView.class);
    public AddUserView(){
        TextField pictureId = new TextField();
        pictureId.setLabel("Picture Id");
        TextField userName = new TextField();
        userName.setLabel("User Name");
        TextField eMail = new TextField();
        eMail.setLabel("Email");
        Button okButton = new Button("Ok");
        okButton.addClickListener(event->{
            User user = new User();
            user.setPicture(UUID.fromString(pictureId.getValue()));
            user.setName(userName.getValue());
            user.setEmail(eMail.getValue());
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity result = restTemplate.postForEntity("http://localhost:8082/user/add", user,String.class);
           logger.warn(result.toString());
        });
        this.add(pictureId, userName, eMail, okButton);
    }
}
