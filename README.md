##Coding Question: Data Store 

Design & implement  a Key-Value (KV) data store that can perform the following
Background
Key is what uniquely identifies an entry in the store. It would always be a string.   

The value can be   
- Primitive → String, Number (Integer, Long, Double, Float) & Boolean
- Collection   
    - List of primitives (Ordered)
    - Set of primitives (Unordered unique)


###Functional requirements
- Primitives
    - Store a value against a key.
    - Fetch the value stored against given key
    - Delete a key
- Collection
    - Store a single value or multiple values against a key.
    - Fetch a single value or multiple values against a key
    - Delete a single value or multiple values against a key
    - Delete a key
- Type safety
    - The data type of the value/primitive is determined at the time of first insert.
    - The data type of the value/collection along with the holder primitive type is determined at the time of first insert.
    - For subsequent updates to the key, the type safety must be enforced.