package com.example.dimagi.bot.rest;

/**
 * A generic class that holds a result success w/ data or an error exception.
 */
public class ApiResult<T> {
    // hide the private constructor to limit subclass types (Success, Error)
    private ApiResult() {
    }

    @Override
    public String toString() {
        if (this instanceof ApiResult.Success) {
            Success success = (Success) this;
            return "Success[data=" + success.getData().toString() + "]";
        } else if (this instanceof ApiResult.Error) {
            Error error = (Error) this;
            return "Error[exception=" + error.getError().toString() + "]";
        }
        return "";
    }

    // Success sub-class
    public final static class Success<T> extends ApiResult {
        private T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    // Error sub-class
    public final static class Error extends ApiResult {
        private Exception error;

        public Error(Exception error) {
            this.error = error;
        }

        public Exception getError() {
            return this.error;
        }
    }
}
