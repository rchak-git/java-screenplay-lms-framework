Act as a Test Automation Engineer.

Analyze the provided HTML DOM snippet for the SauceDemo Login page:

<div class="login_container"><div class="login_logo">Swag Labs</div><div class="login_wrapper" data-test="login-container"><div class="login_wrapper-inner"><div id="login_button_container" class="form_column"><div class="login-box"><form><div class="form_group"><input class="input_error form_input" placeholder="Username" data-test="username" id="user-name" autocorrect="off" autocapitalize="none" type="text" value="" name="user-name"></div><div class="form_group"><input class="input_error form_input" placeholder="Password" data-test="password" id="password" autocorrect="off" autocapitalize="none" type="password" value="" name="password"></div><div class="error-message-container"></div><input class="submit-button btn_action" data-test="login-button" id="login-button" type="submit" value="Login" name="login-button"></form></div></div></div><div class="login_credentials_wrap" data-test="login-credentials-container"><div class="login_credentials_wrap-inner"><div id="login_credentials" class="login_credentials" data-test="login-credentials"><h4>Accepted usernames are:</h4>standard_user<br>locked_out_user<br>problem_user<br>performance_glitch_user<br>error_user<br>visual_user<br></div><div class="login_password" data-test="login-password"><h4>Password for all users:</h4>secret_sauce</div></div></div></div></div>Generate a Java class named `LoginPageUi` in package `com.learningmate.screenplay.apps.saucedemo.ui`.

Define `public static final Target` constants for all key interactive elements on this page:
- Username input field
- Password input field
- Login button
- Error message banner

Use CSS selectors or IDs derived directly from the DOM snippet (preferring `data-test` attributes or unique `id`s).

Import `com.learningmate.screenplay.core.target.Target`.