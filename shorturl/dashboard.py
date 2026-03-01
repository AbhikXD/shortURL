import streamlit as st
import requests
import json
from datetime import datetime

st.set_page_config(page_title="Short URL Dashboard", layout="wide")

st.title("🔗 Short URL Dashboard")
st.markdown("---")

# Configuration
API_URL = "http://localhost:8080"

# Sidebar for settings
with st.sidebar:
    st.header("Settings")
    api_base_url = st.text_input("API Base URL", value=API_URL)
    st.markdown("---")
    st.markdown("### About")
    st.info("This dashboard allows you to create and test short URLs using the Short URL service.")

# Create two columns for layout
col1, col2 = st.columns(2)

# Column 1: Create Short URL
with col1:
    st.header("➕ Create Short URL")
    
    long_url = st.text_input(
        "Enter your long URL",
        placeholder="https://example.com/very/long/url/path",
        key="long_url_input"
    )
    
    if st.button("Create Short URL", key="create_btn", use_container_width=True):
        if not long_url:
            st.error("Please enter a URL")
        elif not (long_url.startswith("http://") or long_url.startswith("https://")):
            st.error("URL must start with http:// or https://")
        else:
            with st.spinner("Creating short URL..."):
                try:
                    response = requests.post(
                        f"{api_base_url}/create",
                        json={"longUrl": long_url},
                        headers={"Content-Type": "application/json"},
                        timeout=10
                    )
                    
                    if response.status_code == 200:
                        short_url = response.text.strip('"')
                        st.success("✅ Short URL Created!")
                        st.code(short_url, language="url")
                        
                        # Store in session for testing
                        st.session_state.last_short_url = short_url
                        st.session_state.last_long_url = long_url
                        
                    else:
                        st.error(f"Error: {response.status_code} - {response.text}")
                        
                except requests.exceptions.ConnectionError:
                    st.error("❌ Cannot connect to API. Make sure the service is running at " + api_base_url)
                except Exception as e:
                    st.error(f"❌ Error: {str(e)}")

# Column 2: Test Short URL
with col2:
    st.header("🧪 Test Short URL")
    
    col2a, col2b = st.columns([3, 1])
    
    with col2a:
        short_url_input = st.text_input(
            "Enter short URL code",
            placeholder="b (from http://localhost:8080/b)",
            key="short_url_input"
        )
    
    with col2b:
        st.write("")  # Align button
        test_btn = st.button("Test", key="test_btn", use_container_width=True)
    
    if test_btn:
        if not short_url_input:
            st.error("Please enter a short URL code")
        else:
            with st.spinner("Testing redirect..."):
                try:
                    # Don't follow redirects to see the Location header
                    response = requests.get(
                        f"{api_base_url}/{short_url_input}",
                        allow_redirects=False,
                        timeout=10
                    )
                    
                    if response.status_code == 302:
                        redirect_url = response.headers.get("Location")
                        st.success("✅ Redirect Found!")
                        st.write("**Status Code:** 302")
                        st.write("**Redirects to:**")
                        st.code(redirect_url, language="url")
                        
                        # Provide a clickable link
                        st.markdown(f"[Click to follow redirect →]({redirect_url})")
                        
                    elif response.status_code == 404:
                        st.warning("⚠️ Short URL not found (404)")
                    else:
                        st.error(f"Unexpected status code: {response.status_code}")
                        
                except requests.exceptions.ConnectionError:
                    st.error("❌ Cannot connect to API. Make sure the service is running.")
                except Exception as e:
                    st.error(f"❌ Error: {str(e)}")

# Last Created URL Section
st.markdown("---")
st.header("📊 Recent Activity")

if "last_short_url" in st.session_state and "last_long_url" in st.session_state:
    col3, col4 = st.columns(2)
    
    with col3:
        st.info(f"**Last Created Short URL:**\n\n{st.session_state.last_short_url}")
    
    with col4:
        st.info(f"**Original URL:**\n\n{st.session_state.last_long_url}")
    
    # Quick test button for last created URL
    if st.button("Test Last Created URL", use_container_width=True):
        with st.spinner("Testing..."):
            try:
                short_code = st.session_state.last_short_url.split("/")[-1]
                response = requests.get(
                    f"{api_base_url}/{short_code}",
                    allow_redirects=False,
                    timeout=10
                )
                
                if response.status_code == 302:
                    redirect_url = response.headers.get("Location")
                    if redirect_url == st.session_state.last_long_url:
                        st.success("✅ URL correctly redirects to original!")
                    else:
                        st.warning(f"⚠️ URL redirects to different destination: {redirect_url}")
                else:
                    st.error(f"Error: Status {response.status_code}")
                    
            except Exception as e:
                st.error(f"Error: {str(e)}")
else:
    st.info("Create a short URL to see it here")

# Footer
st.markdown("---")
st.markdown(
    """
    <div style="text-align: center; color: gray; font-size: 12px;">
    Short URL Dashboard | Testing Tool for Short URL Service
    </div>
    """,
    unsafe_allow_html=True
)
