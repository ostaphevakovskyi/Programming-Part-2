import configparser
from flask_sqlalchemy import SQLAlchemy
from flask import Flask, jsonify, request

application = Flask(__name__)

config = configparser.ConfigParser()
config.read('./products_db.conf')

application.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://' + config.get('DB', 'user') + \
                                                ':' + config.get('DB', 'password') + '@' + \
                                                config.get('DB', 'host') + '/' + config.get('DB', 'db')

application.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = True

mysql = SQLAlchemy()
mysql.init_app(application)


class PackageModel(mysql.Model):
    __tablename__ = "package"
    id = mysql.Column(mysql.Integer, primary_key=True)
    price = mysql.Column(mysql.Integer, nullable=False)
    volume = mysql.Column(mysql.Integer, nullable=False)
    producer = mysql.Column(mysql.String(20), nullable=False)
    material = mysql.Column(mysql.String(20), nullable=False)
    type = mysql.Column(mysql.String(20), nullable=False)

    def __repr__(self):
        return '<Products (%s, %s, %s, %s, %s) >' % (self.price, self.producer, self.volume, self.material, self.type)


@application.route("/")
def hello():
    return "Hello World!"


@application.route('/package', methods=['POST'])
def create_product():
    id = request.json['id']
    price = request.json['price']
    producer = request.json['producer']
    volume = request.json['volume']
    material = request.json['material']
    type = request.json['type']
    curr_session = mysql.session

    package = PackageModel(id=id, price=price, producer=producer, volume=volume, material=material, type=type)

    try:
        curr_session.add(package)
        curr_session.commit()
    except:
        curr_session.rollback()
        curr_session.flush()

    package_id = package.id
    data = PackageModel.query.filter_by(id=package_id).first()

    config.read('./products_db.conf')

    result = [data.price, data.producer, data.volume, data.material, data.type]

    return jsonify(session=result)


@application.route('/package', methods=['GET'])
def get_product():
    data = PackageModel.query.all()

    data_all = []

    for package in data:
        data_all.append([package.price, package.producer, package.volume, package.material, package.type])

    return jsonify(products=data_all)


@application.route('/package', methods=['PATCH'])
def update_product():
    global package
    id = request.get_json['id']
    price = request.get_json['price']
    producer = request.get_json['producer']
    volume = request.get_json['volume']
    material = request.get_json['material']
    type = request.get_json['type']
    curr_session = mysql.session

    try:
        package = PackageModel.query.filter_by(id=id).first()
        package.price = producer
        package.volume = volume
        package.price = price
        package.material = material
        package.type = type
        curr_session.commit()
    except:
        curr_session.rollback()
        curr_session.flush()

    product_id = package.id
    data = PackageModel.query.filter_by(id=product_id).first()

    config.read('./products_db.conf')

    result = [data.price, data.producer, data.volume, data.material, data.type]

    return jsonify(session=result)


@application.route('/package/<int:product_id>', methods=['DELETE'])
def delete_product(product_id):
    curr_session = mysql.session

    PackageModel.query.filter_by(id=product_id).delete()
    curr_session.commit()

    return get_product()


application.run()
