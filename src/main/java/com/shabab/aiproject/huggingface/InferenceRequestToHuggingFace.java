package com.shabab.aiproject.huggingface;

public class InferenceRequestToHuggingFace {
    private String inputs;
    private Options options;

    private Parameters parameters;

    public String getInputs() {
        return inputs;
    }

    public void setInputs(String inputs) {
        this.inputs = inputs;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public InferenceRequestToHuggingFace(String inputs) {
        this.inputs = inputs;
        this.options = new Options(false, true);
        this.parameters = new Parameters(0.9, 0.2,false,3000,6000,5000,8000,2000);
    }

}

record Options(Boolean use_cache, Boolean wait_for_model){}
record Parameters(Double top_p, Double temperature,Boolean return_full_text,int min_length,int max_length,int top_k,long max_new_tokens,int num_return_sequences){}





   /*     "inputs": "you are code asistant.make crud operation in spring boot on student domain",
        "modelType": "TEXT",
        "modelInferenceUrl": "https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.2"
        }*/

/*{"inputs": "You are code asistant.make crud operation in spring boot on student domain"
        ,
        "parameters":{
        "return_full_text":false,
        "temperature":0.2,
        "num_return_sequences":40,
        "max_new_tokens":8000,
        "min_length":15000,
        "max_time":100

        },
        "options":{}*/



