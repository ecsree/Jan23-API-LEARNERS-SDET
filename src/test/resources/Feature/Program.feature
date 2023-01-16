Feature: Program
Background:
Given API url "https://lms-backend-service.herokuapp.com/lms/"

  Scenario Outline: Save Program
  
    When User set body with program_name as<Programname> program_description as<Desc> and program status as<Status> and send  request with <Endpoint>
    Then The new Program is Created  <Statuscode>

    Examples: 
      | Programname                | Desc                   | Status | Statuscode | Endpoint     |
      | Jan23-API_LEARNERS-SDET-7771234 | API_LEARNERS are smart | Active |        201 | /saveprogram |
      | Jan23-API_LEARNERS-SDET-8881234 | Application progming   | Active |        201 | /saveprogram |



  Scenario Outline: Update Program by Id
  
    When User modified body programname as <pgmname> program description as<pgmdesc> and program status as<status2>and send  request with <endpoint2>
    Then user should get valid response <statuscode>
      
      Examples: 
      | pgmname | pgmdesc        | status2 | endpoint2    |statuscode|
      | Jan23-API_LEARNERS-SDET-777771234 | java is not simple | Active  | /putprogram/ |200|
      
      
      
      Scenario Outline: Update Program by name
      
    When User modifies body programname as <pgmname> program description as<pgmdesc> and program status as<status2>and send  request with <endpoint3>
    Then user gets valid response <statuscode>
      
     Examples: 
     | pgmname | pgmdesc        | status2 | endpoint3   |statuscode|
     | Python2532234 | java is not simple | Active  | /program/ |200|
      
      
     Scenario Outline: Delete Program by Id
     
     When User deletes the program with request <endpoint>
     Then User get valid response <statuscode>
     
      Examples: 
     | endpoint   |statuscode|
   		| /deletebyprogid/ |200|
      