MOD = 1073741783
p = [2, 3, 4, 5]
# p = [2]

n = 1000000739579182 - 1
# n = 500 - 1

# result = 0
# for div in p:
#     a = n

#     while a > 0:
#         result += a // div
#         a -= 1

# print(result)


result = 0
for div in p:
    a = n

    while a > 0 and a % div != div - 1:
        # print(a)
        result += a // div
        a -= 1

    # print("final a =", a, a // div)

    a = a // div
    result += (a * (a + 1) // 2) * div

print(result % MOD)
