# TASK

1. Create an API to Find Specific Parking Lot by its ID
Request Method : GET
Request Path : `/parking-lots/{id}`
Condition : 
   - Return 200 : 
     - Return Parking Lots and its assigned vehicle if any
   - Return 404 : 
     - Parking Lots ID not found

2. Create an API to park a Vehicle to a specific parking lot
Request Method : PUT
Request Path: `/parking-lots/{id}/vehicle`
Request Body : Vehicle with registrationNumber and color
Condition : 
   - Return 200 :
     - Vehicle is successfully assigned to a parking lot
   - Return 400 :
     - Parking lot ID is not found
     - Vehicle with such registration number is already exists in our lots

3. Create an API to un-park a Vehicle by its registration number and parking lot id
Request Method : DELETE 
Request Path: `/parking-lots/{parkingLotId}/vehicle/{vehicleRegistrationNumber}`
Condition : 
   - Return 200 : 
     - Vehicle with such registration number and correct parking lot ID is found
   - Return 400 : 
     - No vehicle with such registration number and parking lot id is found

4. Create an API to find a Vehicle with a specific registration number and its parking lot ID (optional)
   Request Method : GET
   Request Path : `/parking-lots/vehicle/{registrationNumber}`
   Condition :
    - Return 200 :
        - Vehicle is found within our lots, return the vehicle details and its parking lot id
    - Return 404 :
        - Vehicle is not found