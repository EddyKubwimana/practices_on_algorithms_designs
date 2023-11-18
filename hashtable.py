class HashTable:
    def __init__(self, size):
        self.size = size
        self.table = [None] * size

    def hash_function(self, key):
        # Primary hash function
        return hash(key) % self.size

    def secondary_hash_function(self, key):
        # Secondary hash function to determine step size
        # Should be relatively prime to the table size
        return 1 + (hash(key) % (self.size - 1))

    def insert(self, key, value):
        index = self.hash_function(key)
        step_size = self.secondary_hash_function(key)

        while self.table[index] is not None:
            # Double hashing: move to the next slot using the secondary hash function
            index = (index + step_size) % self.size

        self.table[index] = (key, value)

    def get(self, key):
        index = self.hash_function(key)
        step_size = self.secondary_hash_function(key)

        while self.table[index] is not None:
            stored_key, value = self.table[index]

            if stored_key == key:
                return value

            # Double hashing: move to the next slot using the secondary hash function
            index = (index + step_size) % self.size

        # Key not found
        raise KeyError(key)

# Example usage:
hash_table = HashTable(3)
hash_table.insert(1, 1)
hash_table.insert(2, 2)
hash_table.insert(5, 3)

print(hash_table.get(1))    
print(hash_table.get(2))    
#print(hash_table.get(5))  

print("Gello")
