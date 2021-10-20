import subprocess
import os
import re

HOME_PATH = "../"
CHECKER_REGEX = r"test run:\s*(\d+),\s*passed:\s*(\d+),\s*failed:\s*(\d+)"
TIMER_REGEX = r"Finished in\s*(\d+\s*\w+)"


def mkpath(*paths):
    return os.path.normpath(os.path.join(*paths))


def runTest(path):
    result = "".join(
        item.decode("utf-8").strip() for item in subprocess.Popen(
            "{}".format(path),
            shell=True,
            stdin=subprocess.PIPE,
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE
        ).communicate()
    )

    matches = list(map(int, re.search(CHECKER_REGEX, result, re.IGNORECASE).groups()))

    return (
        matches[0] == matches[1] and matches[2] == 0,
        re.search(TIMER_REGEX, result, re.IGNORECASE).group(1)
    )


def main():
    cur_folder = os.getcwd()

    for folder in sorted(os.listdir(mkpath(HOME_PATH))):
        if os.path.isdir(mkpath(HOME_PATH, folder)) and folder.startswith("HW"):
            folder = mkpath(HOME_PATH, folder)

            for file in sorted(os.listdir(folder)):
                if os.path.isfile(mkpath(folder, file)) and os.path.splitext(file)[0].endswith("Test"):
                    file = mkpath(folder, file)

                    os.chdir(folder)

                    try:
                        test_results = runTest(file)
                        if test_results[0]:
                            print("{} passed successfuly in {}".format(file, test_results[1]))
                        else:
                            raise AssertionError("At least one test failed")

                    except Exception as e:
                        print("{} failed:".format(file))
                        print(e)

                    os.chdir(cur_folder)
                    # return


if __name__ == "__main__":
    main()
