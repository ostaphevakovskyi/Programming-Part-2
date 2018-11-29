class Manager:
    list_of_packages = []

    def __init__(self):
        pass

    def sort_by_volume(self):
        self.list_of_packages.sort(key=lambda list_of_packages: list_of_packages.volume)

    def sort_by_material(self):
        self.list_of_packages.sort(key=lambda list_of_packages: list_of_packages.material)

    def sort_by_type(self):
        self.list_of_packages.sort(key=lambda list_of_packages: list_of_packages.package_type.value)

    def add_toy(self, package):
        self.list_of_packages += package

    def print_list(self):
        for package in self.list_of_packages:
            print(package)
        print("\n")

