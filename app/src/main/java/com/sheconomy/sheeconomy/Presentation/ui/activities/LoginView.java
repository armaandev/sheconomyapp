package com.sheconomy.sheeconomy.Presentation.ui.activities;

import com.sheconomy.sheeconomy.Network.response.AuthResponse;

public interface LoginView {
    public void setLoginResponse(AuthResponse authResponse);
    public void showErrorResponse();
}
