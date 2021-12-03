from shutil import copyfile, rmtree
import os


ROOT = "../"
KGEORGIY_ROOT = "../../prog-intro/"


def mkpath(*paths):
    return os.path.normpath(os.path.join(*paths))


def removeFromKgeorgiy(path):
    if os.path.isfile(mkpath(KGEORGIY_ROOT, path)):
        os.remove(mkpath(KGEORGIY_ROOT, path))

    elif os.path.isdir(mkpath(KGEORGIY_ROOT, path)):
        rmtree(mkpath(KGEORGIY_ROOT, path))


def listdir(*path):
    return os.listdir(mkpath(ROOT, *path))


def copy(src, dst):
    print("Copying \"{}\" to \"{}\"".format(src, dst))
    src = mkpath(ROOT, src if isinstance(src, str) else mkpath(*src))
    dst = mkpath(KGEORGIY_ROOT, dst if isinstance(dst, str) else mkpath(*dst))

    if not os.path.splitext(dst)[1].strip():
        dirname = dst
        dst = mkpath(dst, os.path.split(src)[1])
    else:
        dirname = os.path.dirname(dst)

    os.makedirs(dirname, exist_ok=True)

    copyfile(src, dst)


def main():
    removeFromKgeorgiy("java-solutions")
    removeFromKgeorgiy("qf-solutions")

    [copy(mkpath("MyClasses", file), ("java-solutions")) for file in listdir("MyClasses") if os.path.splitext(file)[1] == ".java"]

    [copy(mkpath("HW5", file), ("java-solutions")) for file in listdir("HW5") if os.path.splitext(file)[1] == ".java"]

    [copy(mkpath("HW6", file), ("java-solutions")) for file in listdir("HW6") if os.path.splitext(file)[1] == ".java"]

    [copy(mkpath("HW7", "markup", file), ("java-solutions", "markup")) for file in listdir("HW7", "markup") if os.path.splitext(file)[1] == ".java"]

    [copy(mkpath("HW8", file), ("qf-solutions", os.path.splitext(file)[0])) for file in listdir("HW8") if os.path.splitext(file)[1] == ".java"]

    [copy(mkpath("HW9", "md2html", file), ("java-solutions", "md2html")) for file in listdir("HW9", "md2html") if os.path.splitext(file)[1] == ".java"]

    [copy(mkpath("HW10", "game", file), ("java-solutions", "game")) for file in listdir("HW10", "game") if os.path.splitext(file)[1] == ".java"]


if __name__ == "__main__":
    main()
