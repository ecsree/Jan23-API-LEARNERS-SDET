Feature: Program
Background:
Given API url "https://lms-backend-service.herokuapp.com/lms/"


  Scenario Outline: Save Program
  
    When User set body with program_name as<Programname> program_description as<Desc> and program status as<Status> and send  request with <Endpoint>
    Then The new Program is Created  <Statuscode>

    Examples: 
      | Programname                | Desc                   | Status | Statuscode | Endpoint     |
      | Jan23-API_LEARNERS-SDET-511005 | API_LEARNERS are smart | Active |        201 | /saveprogram |
      | Jan23-API_LEARNERS-SDET-523008 | Application progming   | Active |        201 | /saveprogram |



  Scenario Outline: Update Program by Id
    When User modified body programname as <pgmname> program description as<pgmdesc> and program status as<status2>and send  request with <endpoint2>
    Then user should get valid response <statuscode>
      
      Examples: 
      | pgmname 											| pgmdesc        | status2 | endpoint2    |statuscode|
      | Jan23-API_LEARNERS-SDET-51100  | java is not simple | Active  | /putprogram/ |200|
      
      
      Scenario: To get details of all programe using GetAllProgram API
        Given User enter  endpoints
        When User call GetAllProgramAPI with Get HTTP method
        Then response API call got success with status code 200
        And validate the response header : "Server" is "Cowboy" 
        And validate the response header : "Content-Type" is "application/json"