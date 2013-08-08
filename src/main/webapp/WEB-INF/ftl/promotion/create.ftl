<#import "/spring.ftl" as spring />
<@spring.bind "promotion" />
<@spring.bind "categoryOptions" />
<@spring.bind "availabilityOptions" />
<@spring.bind "monthOptions" />
<@spring.bind "dayOptions" />
<@spring.bind "yearOptions" />
<@spring.bind "hourOptions" />
<@spring.bind "minuteOptions" />
<div class="Promotions form">
<br/>
			<form method="post" id="PromotionAddForm" action="<@spring.url "/promotions"/>">
				<fieldset>
					<div class="input text">
						<label for="PromotionOffer">Offer</label>
						<@spring.formInput "promotion.offer", 'maxlength="45" class="span6"'/>
						<@spring.showErrors "promotion.offer", "text-error"/>
					</div>
					<div class="input textarea">
						<label for="PromotionOverview">Overview</label>
						<@spring.formTextarea "promotion.overview", 'rows="6" cols="30" style="display: none;"'/>
					</div>
					<div class="input select">
						<label for="PromotionAvailability">Availability</label>
						<@spring.formSingleSelect "promotion.availability", availabilityOptions/>
					</div>
					<div class="input text">
						<label for="PromotionEpromo">Epromo</label>
						<@spring.formInput "promotion.epromo", 'maxlength="45"'/>
					</div>
					<div class="row date">
						<div class="span6">
							<label for="PromotionEndDateMonth">End Date</label>
							<@spring.formSingleSelect "promotion.endDate.time.month", monthOptions, 'class="span1"'/>
							-
							<@spring.formSingleSelect "promotion.endDate.time.date", dayOptions, 'class="span1"'/>
							-
							<@spring.formSingleSelect "promotion.endDate.time.year", yearOptions, 'class="span1"'/>
							
							<@spring.formSingleSelect "promotion.endDate.time.hours", hourOptions, 'class="span1"'/>
							:
							<@spring.formSingleSelect "promotion.endDate.time.minutes", minuteOptions, 'class="span1"'/>					
						</div>
					</div>
					<div class="row">
						<div class="span8">
							<label for="PromotionCategoryId">Category</label>
							<@spring.formSingleSelect "promotion.category", categoryOptions, 'class="span6"'/>
						</div>
						<div class="span8">
						<select id="PromotionProducts" multiple="multiple" class="span6" name="PromotionProducts">
            			</select>
						</div>
					</div>
				</fieldset>
				<div class="submit">
					<input type="submit" value="Submit" class="btn">
					<a href="<@spring.url "/promotions"/>" data-title="Cancel" class="btn btn-danger"><i class="icon-remove"></i> Cancel</a>
				</div>
			</form>
</div>
<script type="text/javascript">    
    $(document).ready(function() {
        var buttons = ['formatting', '|', 'bold', 'italic', '|', 'unorderedlist', 'orderedlist', 'outdent', 'indent', '|', 'table','link' , '|', 'horizontalrule'];        
        $('textarea').redactor({
            focus: false, 
            buttons: buttons
        });            
        $(document).on('change','#category',function(){                
            $.get('<@spring.url "/promotions/products/0/"/>' + this.value + "?ajax=true",function(data){
                $('#PromotionProducts').html(data);
            });
        });
        $('#PromotionAddForm').find("input[type=text]:first").focus();
     });
</script>

<style>
    .date select {
        margin:0 4px;
    }
    #PromotionEndDateYear {
        margin-right: 20px;
    }    
    .row.date {
        font-size: 1.4em;
        font-weight: bold;
    }    
</style>
