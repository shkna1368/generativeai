package com.shabab.aiproject.bank;


import com.shabab.aiproject.huggingface.ModelType;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Route("/ai-banking")
public class BankView extends VerticalLayout implements TransActionCallBack{


  private final BankModelService modelService;





    Button addNewBtn;
    TextArea textAreaPrompt;
    Span labelResult;


    Grid<BankAccount> bkGrid;



    public BankView(BankModelService modelService) {

        this.modelService = modelService;


        TextField readonlyField = new TextField();
        readonlyField.setWidth(100, Unit.PERCENTAGE);
        readonlyField.setReadOnly(true);
        readonlyField.setValue("Welcome to Beritan AI Banking ");

        textAreaPrompt= new TextArea("Enter your command here");
        labelResult= new Span("");
        textAreaPrompt.setWidth(900, Unit.PIXELS);
        textAreaPrompt.setHeight(200, Unit.PIXELS);
        this.addNewBtn = new Button("Send");
        addNewBtn.addClickListener(buttonClickEvent -> {
            labelResult.setText("");
       var textModelResponses= modelService.sendInferenceWithTextModel(ModelType.TEXT, textAreaPrompt.getValue(),"Mistral-7B-Instruct-v0.2",this);
      // var textModelResponses= modelService.sendInferenceWithTextModel(ModelType.TEXT, textAreaPrompt.getValue(),"flan-t5-large");
           // labelResult.setText("Welecom to Beritan AI Banking System");

            setData(textModelResponses);

          /*  for (TextModelResponse textModelResponse : textModelResponses) {
                labelResult.setText(labelResult.getText() + textModelResponse.getGenerated_text());
            }*/

                });



        bkGrid = new Grid<>();
        bkGrid.setItems(modelService.getAllBanAccount());

       bkGrid.addColumn(BankAccount::getName).setHeader("Name");
       bkGrid.addColumn(BankAccount::getAccountNumber).setHeader("Account Number");
       bkGrid.addColumn(BankAccount::getBranchName).setHeader("Branch Name");
       bkGrid.addColumn(BankAccount::getMobileNumber).setHeader("Mobile Number");
       bkGrid.addColumn(BankAccount::getPassportNumber).setHeader("Passport Number");
       bkGrid.addColumn(BankAccount::getBalance).setHeader("Balance");



        MemoryBuffer memoryBuffer = new MemoryBuffer();
        Upload singleFileUpload = new Upload(memoryBuffer);
        singleFileUpload.addSucceededListener(event -> {
            // Get information about the uploaded file
            InputStream fileData = memoryBuffer.getInputStream();
            String fileName = event.getFileName();
            long contentLength = event.getContentLength();
            String mimeType = event.getMIMEType();
            try {
                byte[] data=fileData.readAllBytes();

            setData(modelService.sendAudi(ModelType.TEXT, data,"Mistral-7B-Instruct-v0.2",this));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // Do something with the file data
            // processFile(fileData, fileName, contentLength, mimeType);
        });




        // build layout
        VerticalLayout actions = new VerticalLayout(readonlyField ,textAreaPrompt,labelResult,addNewBtn,bkGrid,singleFileUpload);
        add(actions);




          // add(new Button("Click me", e -> Notification.show("Hello, Spring+Vaadin user!")));
    }

    public void setData(Map<String, List> data){
List<BankAccount> vims=data.get("bankAccounts");


        bkGrid.setItems(vims);



    }

    @Override
    public void getTransactions(List<Transaction> transactions) {
        Dialog dialog = new Dialog();
        dialog.getElement().setAttribute("aria-label", "Create new employee");

       Grid<Transaction> trGrid = new Grid<>();
        trGrid.setItems(transactions);

        trGrid.addColumn(Transaction::getFrom).setHeader("From");
        trGrid.addColumn(Transaction::getTo).setHeader("To");
        trGrid.addColumn(Transaction::getDate).setHeader("Date");
        trGrid.addColumn(Transaction::getAmount).setHeader("Amount");


      Button button = new Button("Close dialog", e -> dialog.close());



        VerticalLayout verticalLayout = new VerticalLayout(trGrid,button);
        dialog.add(verticalLayout);
dialog.open();

    }
}