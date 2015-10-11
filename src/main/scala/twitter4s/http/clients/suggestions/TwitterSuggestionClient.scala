package twitter4s.http.clients.suggestions

import scala.concurrent.Future

import twitter4s.entities.{User, Category, Suggestions}
import twitter4s.entities.enums.Language
import twitter4s.entities.enums.Language.Language
import twitter4s.http.clients.OAuthClient
import twitter4s.http.clients.suggestions.parameters.SuggestionsParameters
import twitter4s.util.Configurations

trait TwitterSuggestionClient extends OAuthClient with Configurations {

  val suggestionsUrl = s"$apiTwitterUrl/$twitterVersion/users/suggestions"

  def suggestions(slug: String, lang: Language = Language.English): Future[Suggestions] = {
    val parameters = SuggestionsParameters(lang)
    Get(s"$suggestionsUrl/$slug.json", parameters).respondAs[Suggestions]
  }

  def suggestedCategories(lang: Language = Language.English): Future[Seq[Category]] = {
    val parameters = SuggestionsParameters(lang)
    Get(s"$suggestionsUrl.json", parameters).respondAs[Seq[Category]]
  }
  
  def suggestionsMembers(slug: String): Future[Seq[User]] =
    Get(s"$suggestionsUrl/$slug/members.json").respondAs[Seq[User]]

}
