import time

from zeep import Client
client = Client('http://andromeda.fi.muni.cz/~xbatko/homework2?wsdl')

# Author: Jozef Vanický
# UČO: 506486
# Subject: PA053

def account_create(create_new):
    if create_new:
        account_identifier = client.service.createAccount("506486@mail.muni.cz")
    else:
        account_identifier = "dedb7393-ed0a-4b1d-9855-8ffe4f989275"

    print("Account id: " + account_identifier)
    return account_identifier


def get_account_money_balance(account_identifier):
    balance = client.service.balance(account_identifier)
    print("Account balance: " + str(balance) + "\n")
    return balance


def get_account_stock_balance(account_identifier):
    print("Account stocks:")
    for i in client.service.list():
        account_stock_amount = client.service.own(account_identifier, i)
        print("Item: " + i + "\tAmount of stocks on account: " + str(account_stock_amount))
    print("\n")


def get_current_stocks_price():
    print("Market stocks:")
    for item in client.service.list():
        item_info = client.service.info(item)
        print("Item: " + item + "\tPrice: " + str(item_info))
    print("\n")

def monitor_stocks():
    stock_dict = {
        "AAA": [],
        "CEZ": [],
        "CS": [],
        "KB": []
    }

    for s in client.service.list():
        i = 0
        while i < 3:
            i += 1
            if s in stock_dict:
                stock_dict[s].append(client.service.info(s))
            else:
                stock_dict.update({s: client.service.info(s)})
            time.sleep(0.1)
    return stock_dict

# print("How much to finish: " + str(client.service.howMuchToFinish(account_id)))


account_id = account_create(False)
get_account_money_balance(account_id)
get_account_stock_balance(account_id)
get_current_stocks_price()

while client.service.howMuchToFinish(account_id) > 0:
    stock_dict = monitor_stocks()
    for stock in stock_dict:

        if (stock_dict[stock][-1] <= stock_dict[stock][-2]) and (stock_dict[stock][-2] <= stock_dict[stock][-3]) and client.service.own(account_id, stock) > 0:
            client.service.sell(account_id, stock, client.service.own(account_id, stock))
            print("sold " + stock)
            print("How much to finish: " + str(client.service.howMuchToFinish(account_id)))

        if (stock_dict[stock][-1] >= stock_dict[stock][-2]) and (stock_dict[stock][-2] >= stock_dict[stock][-3]) and client.service.info(stock) < client.service.balance(account_id)/2:
            client.service.buy(account_id, stock, 1)
            print("bought " + stock)
            print("How much to finish: " + str(client.service.howMuchToFinish(account_id)))

