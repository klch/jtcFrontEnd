package pet.frontend.testcase2.view;


import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.MultiFileReceiver;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


@Route(value = "upload")
public class UploadView extends VerticalLayout {
    Logger logger = LoggerFactory.getLogger(UploadView.class);
    public UploadView (){
        MemoryBuffer buffer = new MemoryBuffer();
        getUploadFolder();
        Upload upload = new Upload(new MultiFileReceiver() {
            @Override
            public OutputStream receiveUpload(String s, String s1) {
                File file = new File(new File("uploaded-files"), s);
                try {
                    return new FileOutputStream(file);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                    return null;
                }
            }
        });

        upload.addSucceededListener(event -> {
            logger.warn(event.getFileName());
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://localhost:8082/upload");
            // InputStream inputStream = new FileInputStream(getUploadFolder() + "/" + event.getFileName());
            File file = new File(getUploadFolder() + "/" + event.getFileName());
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode((HttpMultipartMode.BROWSER_COMPATIBLE));
            builder.addBinaryBody("file", file, ContentType.DEFAULT_BINARY, event.getFileName());
            builder.addTextBody("name", event.getFileName());
            HttpEntity entity = builder.build();
            post.setEntity(entity);
            Text text = new Text("TEXT");
            try {
                HttpResponse response = httpClient.execute(post);
                HttpEntity responseEntity = response.getEntity();
                String responseString = EntityUtils.toString(responseEntity, "UTF-8");
                text.setText(responseString);
                this.add(text);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.add(upload);

    }
    private static File getUploadFolder() {
        File folder = new File("uploaded-files");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }
}
