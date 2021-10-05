PATTERN = "Читать документацию может быть на удивление полезно!"
SMALL_REPEAT_COUNT = 10_000_000
# SMALL_REPEAT_COUNT = 863

pattern_hashCode = 467005932

# ================================================================

hashCode = 0

power_const = (31 ** 7) * (961 ** 45)
const_2_31 = 2 ** 31
const_2_32 = 2 ** 32

print(SMALL_REPEAT_COUNT)
cnt = 0

for i in range(SMALL_REPEAT_COUNT):
    # for letter in PATTERN:
    #     if letter == ' ' or letter == '!':
    #         hashCode *= 31
    #     else:
    #         hashCode *= 961
    hashCode = ((
        hashCode * power_const + pattern_hashCode
    ) + const_2_31) % const_2_32 - const_2_31
    # print(hashCode)
    cnt += 1

print(cnt)

print(hashCode)
