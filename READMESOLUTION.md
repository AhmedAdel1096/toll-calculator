## Assumptions & Notes
* This solution assumes that the fee are calculated for each vehicle for one day, since there is ambiguity
  between the documentation in the provided code I choose to go with the logical assumption that the input dates
  are in the same day. (If the business requires that the application will require different days, then this
  implementation will require grouping dates by day and using the current implementation)
  
* Also Assumes that the car passes during the day, so the dates array is sorted in an ascending order
* Tests are in the tests folder under the root path
* Added external libraries log4j & junit
* Used JDK 17