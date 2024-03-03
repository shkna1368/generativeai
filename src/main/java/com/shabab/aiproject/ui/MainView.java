package com.shabab.aiproject.ui;


import com.shabab.aiproject.huggingface.ModelService;
import com.shabab.aiproject.huggingface.ModelType;
import com.shabab.aiproject.huggingface.TextModelResponse;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.util.List;

@Route
public class MainView extends VerticalLayout {


  private final   ModelService modelService;

ModelType selectedModelType=ModelType.TEXT;



    Button addNewBtn;
    ComboBox<String> comboBox;
    ComboBox<String> comboBoxLlmType;
    ComboBox<String> llmType;
    TextArea textAreaResult;
    TextArea textAreaPrompt;
    Image image;
List<String > textToTextllms=List.of("Mistral-7B-Instruct-v0.2","Bloom","flan-t5-large","gemma-7b");
List<String > textToImagellms=List.of("stable-diffusion-v1-5","playground-v2.5-1024px-aesthetic");
List<String > textToCodellms=List.of("Mistral-7B-Instruct-v0.2","starcoder2-15b");
List<String > llmsType=List.of("TextToText","TextToImage","TextToCode");

    public MainView(ModelService modelService) {

        this.modelService = modelService;
        textAreaPrompt= new TextArea("Enter your command here");
        textAreaPrompt.setWidth(900, Unit.PIXELS);
        textAreaPrompt.setHeight(200, Unit.PIXELS);
        this.addNewBtn = new Button("Generate");

        addNewBtn.addClickListener(buttonClickEvent -> {

            if (selectedModelType.equals(ModelType.TEXT)||selectedModelType.equals(ModelType.CODE)) {
                TextModelResponse[] textModelResponses=      modelService.sendInferenceWithTextModel(selectedModelType,textAreaPrompt.getValue(),comboBox.getValue());
                textAreaResult.setValue("");
                for (TextModelResponse textModelResponse : textModelResponses) {
                    textAreaResult.setValue(textAreaResult.getValue() + textModelResponse.getGenerated_text());
                }

            }

  if (selectedModelType.equals(ModelType.IMAGE)) {
          byte[] bytes=      modelService.sendInferenceWithModel(textAreaPrompt.getValue(),comboBox.getValue());


      StreamResource resource = new StreamResource("", () -> new ByteArrayInputStream(bytes));

      image.setSrc(resource);

  }


        });


        comboBoxLlmType = new ComboBox<>();
        comboBoxLlmType.setAllowCustomValue(true);
        comboBoxLlmType.setWidth(200, Unit.PIXELS);
        comboBoxLlmType.setItems(llmsType);
        comboBoxLlmType.setValue(llmsType.iterator().next());


        comboBoxLlmType.addValueChangeListener(event -> {

                 String   selectedLlmType = event.getValue();

                    if (selectedLlmType.equals("TextToText")) {
                      selectedModelType=ModelType.TEXT;
                        comboBox.setItems(textToTextllms);
                        comboBox.setValue(textToTextllms.iterator().next());

                    } else if (selectedLlmType.equals("TextToImage")) {
                        selectedModelType=ModelType.IMAGE;
                        comboBox.setItems(textToImagellms);
 comboBox.setValue(textToImagellms.iterator().next());
                    } else if (selectedLlmType.equals("TextToCode")) {
                        selectedModelType=ModelType.CODE;
                        comboBox.setItems(textToCodellms);
                        comboBox.setValue(textToCodellms.iterator().next());

                    }


                }
            );




        comboBox = new ComboBox<>();
        comboBox.setAllowCustomValue(true);
        comboBox.setWidth(400, Unit.PIXELS);
        comboBox.setItems(textToTextllms);
        comboBox.setValue(textToTextllms.iterator().next());






        textAreaResult = new TextArea();
        textAreaResult.setWidth(700, Unit.PIXELS);
        textAreaResult.setHeight(700, Unit.PIXELS);

        textAreaResult.setMaxLength(20000);
        textAreaResult.setValueChangeMode(ValueChangeMode.EAGER);



         image = new Image("", "");

        HorizontalLayout horizontalLayout=new HorizontalLayout(textAreaResult,image);


        // build layout
        VerticalLayout actions = new VerticalLayout(textAreaPrompt,comboBoxLlmType,comboBox,addNewBtn);
        add(actions,horizontalLayout);

       // add(new Button("Click me", e -> Notification.show("Hello, Spring+Vaadin user!")));
    }
}