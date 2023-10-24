from flask import Flask

name = "Test"
app = Flask(__name__)

@app.route("/")
def hello_world():
    return "<h1>Hello Games!</h1>"

@app.route("/json")
def json_thing():
    return {
        'hello': 'world',
        'games': True,
        'arr': [ 1, 2, 3, 4 ]
    }

if __name__ == '__main__':
    app.run(debug = True)
