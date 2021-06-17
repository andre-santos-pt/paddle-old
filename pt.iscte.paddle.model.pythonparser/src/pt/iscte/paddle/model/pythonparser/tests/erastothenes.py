# Return the number of prime numbers between the range (2, n)
# Model solution for the Sieve of Eratosthenes exercise

def sieve_of_eratosthenes(n):

    if n < 2:
        return 0

    primes = [True for i in range(n + 1)] # boolean array for prime numbers
    primes[0] = False # 0 and 1 are not prime numbers
    primes[1] = False

    p = 2
    while(p * p <= n):
        # The number is a prime if the primes[p] is not changed
        if(primes[p]):
            # Update all multiples of p
            for i in range(p * 2, n + 1, p):
                primes[i] = False
        p += 1

    # Print all the prime numbers and return the number of primes
    # Note: printing all the prime numbers is optional
    nof_primes = 0
    for p in range(n + 1):
        if primes[p]:
            # print(p)
            nof_primes += 1

    return nof_primes

# Driver program, you can test your solution with different values of n
if __name__ == '__main__':
    n = 10
    print("The prime numbers between the range (2, {:d}) are:".format(n))
    nof_primes = sieve_of_eratosthenes(n)
    print("The number of primes is {:d}".format(nof_primes))

