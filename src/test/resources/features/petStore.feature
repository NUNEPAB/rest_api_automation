Feature: REST API Automation

  Background:
    Given setup is done

  Scenario: Get available pets
    When search for "available" pets in the store
    Then list of available pets is returned

  Scenario: Post new available pet
    When post new available pet in the store
    Then new pet is added

  Scenario: Update pet status to sold
    When update new pet status to "sold"
    Then new pet status is updated to "sold"

    Scenario: Delete added pet
      When delete pet added
      Then pet is removed from store