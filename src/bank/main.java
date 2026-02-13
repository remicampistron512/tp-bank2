import bank.service.BankingService;

void main() {
  var service = new BankingService(/* customerDao, accountDao, ... */);
  var menus = new bank.view.ConsoleUi(service);
  menus.run();

}