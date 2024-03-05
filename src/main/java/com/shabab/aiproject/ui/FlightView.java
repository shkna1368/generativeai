package com.shabab.aiproject.ui;


import com.shabab.aiproject.flight.Flight;
import com.shabab.aiproject.flight.FlightModelService;
import com.shabab.aiproject.huggingface.ModelService;
import com.shabab.aiproject.huggingface.ModelType;
import com.shabab.aiproject.huggingface.TextModelResponse;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Route("/flight")
public class FlightView extends VerticalLayout {


  private final FlightModelService modelService;





    Button addNewBtn;
    TextArea textAreaPrompt;
    Span labelResult;
    Grid<Flight> grid;





    public FlightView(FlightModelService modelService) {

        this.modelService = modelService;
        textAreaPrompt= new TextArea("Enter your command here");
        labelResult= new Span("");
        textAreaPrompt.setWidth(900, Unit.PIXELS);
        textAreaPrompt.setHeight(200, Unit.PIXELS);
        this.addNewBtn = new Button("Send");
        addNewBtn.addClickListener(buttonClickEvent -> {
            labelResult.setText("");
var textModelResponses= modelService.sendInferenceWithTextModel(ModelType.TEXT, textAreaPrompt.getValue(),"Mistral-7B-Instruct-v0.2");
            labelResult.setText("");

            setData(textModelResponses);


          /*  for (TextModelResponse textModelResponse : textModelResponses) {
                labelResult.setText(labelResult.getText() + textModelResponse.getGenerated_text());
            }*/

                });


         grid = new Grid<>();
        grid.setItems(modelService.getAll());

        grid.addColumn(Flight::getPassengerName).setHeader("Name");
        grid.addColumn(Flight::getDate).setHeader("Date");
        grid.addColumn(Flight::getFrom).setHeader("From");
        grid.addColumn(Flight::getTo).setHeader("To");
        grid.addColumn(Flight::getTime).setHeader("Time");
        grid.addColumn(Flight::getPassport).setHeader("Passport");


/*        final BeanItemContainer<Flight> ds =
                new BeanItemContainer<Flight>(Flight.class, modelService.getAll());
        Grid grid = new Grid("Employees", ds);*/



        // build layout
        VerticalLayout actions = new VerticalLayout(textAreaPrompt,labelResult,addNewBtn,grid);
        add(actions);

       // add(new Button("Click me", e -> Notification.show("Hello, Spring+Vaadin user!")));
    }

    public void setData(List<Flight> data){
   grid.setItems(data);


    }
}