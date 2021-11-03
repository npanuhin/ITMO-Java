import os

HOME = "../"
EXTENSIONS = (".java", ".py", ".cmd")


def mkpath(*paths):
    return os.path.normpath(os.path.join(*paths))


for path, folders, files in os.walk(mkpath(HOME)):
    for filename in files:
        if filename.endswith(EXTENSIONS):
            # print(mkpath(path, file))

            with open(mkpath(path, filename), 'r', encoding="utf-8") as file:
                content = file.read()

            with open(mkpath(path, filename), 'w', encoding="utf-8") as file:
                file.write(content.replace('\t', ' ' * 4))