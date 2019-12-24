package pet.frontend.testcase2.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Route(value = "getuser")
public class GetUserView extends VerticalLayout {
    public GetUserView(){
        TextField userIdField = new TextField();
        Button okButton = new Button();
        Text text = new Text("Text");
        okButton.addClickListener(event->{
            String userId = userIdField.getValue();
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:8082/user/" + userId, String.class);
            text.setText(result.toString());
        });

        this.add(userIdField, okButton, text);
    }
}
