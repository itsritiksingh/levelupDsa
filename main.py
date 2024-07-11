import os
import re
import random


def find_java_files(directory):
    java_files = []
    for root, dirs, files in os.walk(directory):
        print(root, dirs, files)
        for file in files:
            if file.endswith(".java"):
                java_files.append(os.path.join(root, file))
    return java_files


def extract_urls_from_java(file_path):
    urls = []
    with open(file_path, "r") as f:
        lines = f.readlines()
        comment_regex = r"//\s*(https?://\S+)"
        for line in lines:
            match = re.search(comment_regex, line)
            if match:
                urls.append(match.group(1))
    random.shuffle(urls)
    return urls


def gather_urls_in_java_files(directory):
    java_files = find_java_files(directory)
    all_urls = []
    for java_file in java_files:
        urls = extract_urls_from_java(java_file)
        all_urls.extend(urls)
    return all_urls


current_directory = "."  # Change this to the directory you want to search in
urls = gather_urls_in_java_files(current_directory)
for url in urls:
    with open("output.txt", "a") as op:
        op.write(url + "\n")
