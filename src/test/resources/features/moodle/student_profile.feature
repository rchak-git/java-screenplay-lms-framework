Feature: Moodle User Profile Verification

Feature: Moodle User Profile Verification

  Scenario: Verify user full name on profile page
    Given "Rajib" is logged into Moodle as a student with credentials:
      | username | password  |
      | student  | sandbox24 |
    When he navigates to his user profile page
    Then he should see his profile name as "Sam Student"



  Scenario: TC_PROFILE_02 - Verify Student Profile Details on LMS
    Given "Rajib" is logged into Moodle as a student with credentials:
      | username | password  |
      | student  | sandbox24 |
    When he navigates to his user profile page
    And he clicks on the edit profile link

    And he updates his profile details:

      | City/town | Brussels |
      | Country   | Belgium |
    Then his profile details should match:
      | Email address | student@moodle.a (Visible to other course participants) |
      | Country        | Belgium          |
      | City/town      | Brussels         |
      | Timezone       | Australia/Perth  |

  Scenario: TC_PROFILE_03 - Successfully update city/town and country in user profile

    Given "Rajib" is logged into Moodle as a student with credentials:

      | username | password  |

      | student  | sandbox24 |

    When he navigates to his user profile page

    And he clicks on the edit profile link

    And he updates his profile details:

      | City/town | Antwerp |

      | Country   | Belgium |

    Then he should see his profile name as "Sam Student"

    And his profile details should match:

      | City/town | Antwerp |

      | Country   | Belgium |

    @hybrid
  Scenario: TC_HYBRID_001 - Seed New Student via REST API and Verify Profile Details via UI

    Given "Rajib" has seeded a new student account via API:

      | username  | testuser_rajib110    |

      | password  | Test@12345!           |

      | firstname | Rajib                 |

      | lastname  | Automation            |

      | email     | rajib.test1ABC@example.com |

    And "Rajib" is logged into Moodle as a student with credentials:

      | username | testuser_rajib110 |

      | password | Test@12345!      |

    When he navigates to his user profile page

    Then "his" profile details should match:

      | Firstname     | Rajib                  |

      | Lastname      | Automation             |

      | Email address | rajib.test1@example.com |


