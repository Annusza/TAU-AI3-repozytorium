*** Settings ***

Documentation     Tests for login validation

Resource          keys.robot

Suite Setup       Open navigator in desktop resolution                       
Suite Teardown    Close navigator


***Variables***

${email}       kwakwa@hauhau.com
${passwd}      Qwerty


*** Test Cases ***

Scenario 01: Login - success
    Login with valid credentials    ${email}
    ...                             ${passwd}

Scenario 02: Login - failed
    [Template]                      Login with invalid credentials
    invalid                         ${passwd}                       Invalid email address.
    ${email}                        invalid                         Authentication failed.
    ${empty}                        ${empty}                        An email address required.
    ${email}                        ${empty}                        Password is required.
    ${empty}                        ${passwd}                       An email address required.