1. we have two demo videos in this final project, since the free version of
   Screen Recorder has a litmit of 15mins.
2. put 'finalproject' folder under C:\apache-tomcat-7.0.34\webapps
3. make sure your mysql is the latest version and the version of mongodb is 4.0
4. you should import database and table/schema from our uploaded folder:'Dump20181130'
5. you should check your java version first and make sure it matches with my
   'env-setup-for-tomcat.txt' file
6. make sure all the files mentioned in 'env-setup-for-tomcat.txt' already exist
   and work fine in your PC
7. copy all contents of 'env-setup-for-tomcat.txt' (I have already attached it in this folder) 
   into your cmd and run
8. then first start up MySQL server, import my data into your database, 
   second startup MongoDB server, in the end, startup the tomcat server
9. java: 6186 lines of code
	
   javascript: 102 lines of code
   total: 6288 lines of code 

10.features implemented
	
	Assignment1:
		Build servlet-based web application (Implemented)
		Website has different roles (Implemented)
		Website has different types of products (Implemented)
		Manager can Add/Delete/Update products (Implemented)
		Salesman can create Customer accounts and can Add/Delete/Update customers' orders (Not Implemented)
			(we implemented manager to cancel customers' orders)
		Every product might have accessories that could be bought separately (Not Implemented)
			(accessories not applicable to our car rental/sharing web application)
		The customer must be able to create an account online (Implemented)
		The customer must be able to place an order online, check the status of the order, or cancel the order (Implemented)
		The customer will pay using a credit card  (Implemented)
	Assignment2: 
		Accounts login information store in SQL database (Implemented)
		Customers transactions/orders store in SQL database (Implemented)
		All order updates to insert/delete/update orders reflected in MySQL (Implemented)
		Customer are able to submit product reviews (Implemented)
		Product reviews stored in NoSQL database - MongoDB (Implemented)
		Add Trending & Data Analytics feature (Partially Implemented)
			(we implemented TopRatedCar based on reviews)
	Assignment3:
		Inventory Report (Implemented)
			(CarInventory shows all the cars that are currently in our database. Unlike electronic products,
			 every car will be displayed as a unique product, so, there is no stock in our web application)
		Sales Report (Implemented)
	Assignment4:
		Auto-Completion (Implemented)
			(Implemented for manager to search for cars to Update/Delete)
	Assignment5:
		Deal Match Guarantees (Implemented)
			(Implemented as matched Automotive News)
		Recommender feature (Implemented)
			(Implemented as recommending the same type of car, max number of recommendations is set to 3)
