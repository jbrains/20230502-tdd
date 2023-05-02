# Add Fractions

- 1/8 + 3/8 = 1/2
- 0/1 + 0/1 = 0/1    # identity element (0)
- 0 + 0 = 0      # backwards compatible with integers
- 1/2 + 1/2 = 1    # result of 2/2 becomes 1
- 1/2 + 1/3 = 5/6    # denominators with no common factors
- 2/5 + 3/10 = 7/10  # denominators with common factors
- 9/14 + 2/21 = 41/42    # denominators with common factors, but not a multiple of the other
- 1/5 + 2/5 = 3/5    # adding fractions is the same as only adding the numerators
- 6/1 + 7/1 = 13/1
- 6 + 7 = 13
- 6 + 0 = 6
- 0 + 7 = 7

## Create Fractions

### Zero denominator is not allowed

Perhaps our constructor for Fraction values should return Maybe<Fraction>.

- 0/0 (x)    # zero denominator is not allowed
- 3/0 (x)

## Equal Fractions

- 5/-7 -> -5/7 # Prefer making the denominator always positive
- 5/-7 = -5/7
- -2/-5 = 2/5

### n/n = 1

- 2/2 = 1
- 8/8 = 1
- -15/-15 = 1

### n/1 = n

- 7/1 = 7
- -9/1 = -9
- 1/1 = 1
- 0/1 = 0

