# Author: Jozef Vanický
# UČO: 506486
# Subject: PA053

from flask import Flask, request
import requests

ngrok_url = "http://f6cbe70b5fef.ngrok.io/"
queries = ['queryAirportTemp', 'queryStockPrice', 'queryEval']
app = Flask(__name__)


def get_weather(iata_data):
    url = "https://weatherapi-com.p.rapidapi.com/current.json"
    querystring = {"q": iata_data}
    headers = {'x-rapidapi-key': "7525c5088fmshcf6e2143f5793ccp12d834jsnb54e9a16f1aa",
               'x-rapidapi-host': "weatherapi-com.p.rapidapi.com"}
    response = requests.request("GET", url, headers=headers, params=querystring)
    return response.json().get("current").get("temp_c")


def get_stock_price(stock_name):
    stock_url = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-summary"
    headers = {
        'x-rapidapi-key': "cf87ab6cd4msh24100c38432b903p1bfe17jsn6a26d33ba928",
        'x-rapidapi-host': "apidojo-yahoo-finance-v1.p.rapidapi.com"
    }
    response_stock = requests.request("GET", url=stock_url, headers=headers, params={"symbol": stock_name})
    if response_stock.status_code == 200:
        result = response_stock.json()
        return result.get('financialData').get("currentPrice").get("raw")
    return None


@app.route('/', methods=['GET'])
def index():
    response = None
    result = None
    for q in queries:
        response = str(request.args.get(q))
        if response != 'None':
            if q == 'queryAirportTemp' and len(response) == 3:
                response = response.upper()
                result = get_weather(response)
            if q == 'queryEval':
                result = eval(response)
            if q == "queryStockPrice":
                result = get_stock_price(response)
            return "<result>" + str(result) + "</result>", 200, {'Content-Type': 'application/xml; charset=utf-8'}
    return "<result>" + "Fck, error occurred." + "</result>", 400, {'Content-Type': 'application/xml; charset=utf-8'}


app.run(host="127.0.0.1", port=8080)
