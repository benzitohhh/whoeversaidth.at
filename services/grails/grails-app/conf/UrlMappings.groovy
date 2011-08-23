class UrlMappings {

	/*
	 * page 183
	 */
	
	static mappings = {
		
		"/score/$fbid?" {
			controller = "score"			
			constraints {
				action = [GET:"showScores", POST:"saveScores"]
			}
		}
		
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
