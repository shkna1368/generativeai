package com.shabab.aiproject.huggingface;



public class InferenceRequest {
    private String inputs;
    private ModelType modelType;
    private String modelInferenceUrl;

    public String getInputs() {
        return inputs;
    }

    public void setInputs(String inputs) {
        this.inputs = inputs;
    }

    public ModelType getModelType() {
        return modelType;
    }

    public void setModelType(ModelType modelType) {
        this.modelType = modelType;
    }

    public String getModelInferenceUrl() {
        return modelInferenceUrl;
    }

    public void setModelInferenceUrl(String modelInferenceUrl) {
        this.modelInferenceUrl = modelInferenceUrl;
    }
}