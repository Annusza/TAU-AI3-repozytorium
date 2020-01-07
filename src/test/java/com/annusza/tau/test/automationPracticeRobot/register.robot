*** Settings ***

Documentation     Test suite to validate the registration action

Resource          keys.robot

Suite Setup       Open navigator in mobile resolution 
Suite Teardown    Close navigator


***Variables***

${email_create}            kwakwakwakwa@hauhau.com
${customer_firstname}      Dana
${customer_lastname}       Kralova 
${passwd}                  Qwerty
${address1}                ul. Zapiecek 15
${city}                    Ghibli
${postcode}                77777
${phone_mobile}            1234567890


*** Test Cases ***

Scenario 01: Registration - success
    Register with valid credentials    ${email_create}
    ...                                ${customer_firstname}     
    ...                                ${customer_lastname}       
    ...                                ${passwd}                  
    ...                                ${address1}               
    ...                                ${city}                   
    ...                                ${postcode}               
    ...                                ${phone_mobile}    

Scenario 02: Registration - success (with email validation)
    [Template]                 Register with invalid email
    ${email_create}            An account using this email address has already been registered. Please enter a valid password or request a new one.
    ${empty}                   Invalid email address.
    invalid                    Invalid email address.  
    
Scenario 03: Registration - failed (formular of registration validation)
    Register with invalid credentials    654
    ...                                  333   
    ...                                  xxx   
    ...                                  ${empty}   
    ...                                  ^&^   
    ...                                  222   
    ...                                  1111 
    ...                                  There are 5 errors   