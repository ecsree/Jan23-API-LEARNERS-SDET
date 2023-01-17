Feature: Batch

  Background: 
    Given A valid API url "https://lms-backend-service.herokuapp.com/lms/"

  Scenario Outline: Create Batches
    When User sent payload with batch_name as<batchName> batch_description as<Desc> and batch status as<Status> and send  request with <Endpoint>
    Then The new batch is Created <statusCode>

    Examples: 
      | batchName                      | Desc            | Status | Endpoint | statusCode |
      | Jan23-API_Learners-SDET-SDET30 | RestAPI Batch01 | Active | /batches |        201 |
      | Jan23-API_Learners-SDET-SDET31 | Postman Batch01 | Active | /batches |        201 |

  Scenario Outline: update the batch by id
    When User send payload with batch_name as <Name> batch_description as<desc> and batch status as<status> and send  request with <endpoint>
    Then  batch is Updated <statuscode>

    Examples: 
      | Name                            | desc             | status | endpoint  | statuscode |
      | Jan23-API_Learners-SDET-SDET411 | RestAPI Batch009 | Active | /batches/ |        200 |

  Scenario: Get Batches by batchId
    When User send a request with end point
    Then user should get valid response

  Scenario: Delete Batches by batchId
    When User send a request with end point to delete a batch
    Then the batch should get deleted
