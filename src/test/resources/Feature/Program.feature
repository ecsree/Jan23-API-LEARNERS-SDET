Feature: Program

  Scenario Outline: Save Program
    Given API url "https://lms-backend-service.herokuapp.com/lms/"
    When User set body with program_name as<Programname> program_description as<Desc> and program status as<Status> and send  request with <Endpoint>
    Then The new Program is Created  <Statuscode>

    Examples: 
      | Programname                | Desc                   | Status | Statuscode | Endpoint     |
      | Jan23-API_LEARNERS-SDET-92 | API_LEARNERS are smart | Active |        201 | /saveprogram |
      | Jan23-API_LEARNERS-SDET-93 | Application progming   | Active |        201 | /saveprogram |
